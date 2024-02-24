#!/usr/bin/env groovy

pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }
    stages {
        stage('build jar') {
            steps {

                script {

                    echo 'Building the application ...'
                    sh 'mvn package'
                }

            }
        }
        stage('build image') {
            steps {
                script {
                    echo 'Building the docker image...'
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'docker build -t uzair102/u_repo:jma-2.0 .'
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh 'docker push uzair102/u_repo:jma-2.0'
                    }
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    echo 'deploying docker image to EC2...'
                    def dockerComposecmd = "docker-compose -f docker-compose.yaml up"
                
                    sshagent(['ec2-server-key']) {
                        sh "scp docker-compose.yaml ec2-user@3.111.144.185"
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@3.111.144.185 ${dockerComposecmd}"
                    }
                }
            }
        }
    }
}

