pipeline {
    agent { label 'dockerhost' }
    environment {
        CI = 'true'
        DOCKER_REPO_NAME = "kiva/kiosa-dev"
        TAG_NAME = "${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
        TAGGED_IMAGE_NAME = "${env.DOCKER_REPO_NAME}:${env.TAG_NAME}"
    }
    stages {
        stage('Test and build') {
            steps {
                echo 'Building and testing...'
                withDockerContainer(image: 'openjdk:8-jdk-alpine', args: '-v $HOME/.gradle:/root/.gradle') {
                    sh "./gradlew build"
                }
            }
        }
        stage('Build production image') {
            steps {
                echo 'Building...'
                sh "cp build/libs/*.jar app.jar"
                sh "docker build -t ${TAGGED_IMAGE_NAME} -f Dockerfile.prod ."
            }
        }
        stage('Publish') {
            steps {
                echo 'Publishing...'
                withDockerRegistry([credentialsId: "ledlie-docker-hub-creds", url: ""]) {
                    sh "docker push ${TAGGED_IMAGE_NAME}"
                }
            }
        }
    }
}
