pipeline {
    agent none
    environment {
        CI = 'true'
        DOCKER_REPO_NAME = "kiva/kiosa-dev"
        TAG_NAME = "${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
        TAGGED_IMAGE_NAME = "${env.DOCKER_REPO_NAME}:${env.TAG_NAME}"
    }
    stages {
        stage('Test, build, and publish') {
            stages {
                stage('Test and build') {
                    agent {
                        docker {
                            image 'gradle:jdk8-alpine'
                            args '-v $HOME/.gradle:/root/.gradle'
                        }
                    }
                    steps {
                        echo 'Building and testing...'
                        sh "gradle build"
                    }
                }
                stage('Build production image') {
                    agent { label 'dockerhost' }
                    steps {
                        echo 'Building...'
                        sh "cp build/libs/*.jar app.jar"
                        sh "docker build -t ${TAGGED_IMAGE_NAME} -f Dockerfile.prod ."
                    }
                }
                stage('Publish') {
                    agent { label 'dockerhost' }
                    steps {
                        echo 'Publishing...'
                        withDockerRegistry([credentialsId: "ledlie-docker-hub-creds", url: ""]) {
                            sh "docker push ${TAGGED_IMAGE_NAME}"
                        }
                    }
                }
            }
        }
    }
}
