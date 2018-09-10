pipeline {
    agent none
    environment {
        CI = 'true'
        TAG_NAME = "${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
    }
    stages {
        stage('Test') {
            agent {
                dockerfile {
                    args '-v $HOME/.gradle:/root/.gradle'
                }
            }
            steps {
                echo 'Testing...'
                sh "./gradlew build"
            }
        }
        stage('Build') {
            agent { label 'dockerhost' }
            steps {
                echo 'Building...'
                sh "docker build -t kiva/kiosa-dev -f Dockerfile.prod ."
            }
        }
        stage('Publish') {
            agent { label 'dockerhost' }
            steps {
                echo 'Publishing...'
                // are we assuming that the publish steps happens on the same node as the build step?
                // we might want to combine the steps into one or otherwise require the same node
                sh "docker tag kiosa-dev kiva/kiosa-dev:${TAG_NAME}"
                withDockerRegistry([ credentialsId: "ledlie-docker-hub-creds", url: "" ]) {
                    sh "docker push kiva/kiosa-dev:${TAG_NAME}"
                }
            }
        }
    }
}