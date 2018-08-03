pipeline {
    agent {
        dockerfile true
    }
    environment {
        CI = 'true'
    }
    stages {
        stage('Install') {
            steps {
                echo 'Installing..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
    }
}