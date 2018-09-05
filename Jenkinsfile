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
                sh "./gradlew test"
            }
        }
        stage('Build') {
            agent any
            steps {
                echo 'Building...'
                sh "docker build -t kotlin-demo ."
            }
        }
        stage('Publish') {
            agent any
            steps {
                echo 'Publishing...'
                withDockerRegistry([ credentialsId: "ledlie-docker-hub-creds", url: "" ]) {
                    sh "docker push kotlin-demo"
                }
            }
        }
    }
}