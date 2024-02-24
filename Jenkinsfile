#!/usr/bin/env groovy


pipeline {
    agent any

    stages {
        stage('build app') {
            steps {
               script {
                  echo 'testing the application ...'
                  
               }
            }
        }
        stage('build image') {
            steps {
                script {
                   echo 'building the application...'
                  
                }
            }
        }
        stage('deploy') {
            steps { 
                script {
                    echo 'deploying docker image to EC2...'
                    def dockerCmd = 'docker run -p3000:80 -d uzair102/u_repo:rna-1.0'
                    sshagent(['ec2-server-key']) {
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@3.111.144.185 ${dockerCmd}"
                    }
                }
            }
        }
    }
} 
