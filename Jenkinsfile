pipeline {
    agent {
        dockerfile true
    }
    environment {
        CI = 'true'
    }
    stages {
        stage('Test') {
            steps {
                echo 'Testing..'
                sh "./gradlew test"
            }
        }
    }
}