pipeline {
    agent {
        dockerfile {
            args '-v $HOME/.gradle:/root/.gradle'
        }
    }
    environment {
        CI = 'true'
    }
    stages {
        stage('Test') {
            steps {
                echo 'Testing...'
                sh "./gradlew test"
            }
        }
        stage('Build') {
            steps {
                echo 'Building...'
                sh "docker build -t kotlin-demo ."
            }
        }
        stage('Publish') {
            steps {
                echo 'Publishing...'
                withDockerRegistry([ credentialsId: "ledlie-docker-hub-creds", url: "" ]) {
                    sh "docker push kotlin-demo"
                }
            }
        }
    }
}