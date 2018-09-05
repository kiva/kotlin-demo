pipeline {
    agent none
    environment {
        CI = 'true'
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
                withDockerRegistry([ credentialsId: "ledlie-docker-hub-creds", url: "" ]) {
                    sh "docker push kiva/kiosa-dev:latest"
                }
            }
        }
    }
}