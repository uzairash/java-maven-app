#!/usr/bin/env groovy
def gv 

pipeline {
    
    agent any
    tools {
        maven 'maven-3.9'
    }
    stages {

        stage('init') {
            steps {
                script {
                    echo "hello world Paji"
                    gv = load "scripts.groovy"
                }

            }
        }
        stage('test') {
            steps {
                script {
                    gv.test()
                }

            }
        }
        stage('build jar') {
            when {
                    expression {
                        BRANCH_NAME == 'master'
                    }
                }
            steps {
                
                script {
                    gv.buildjar()
                }

            }
        }
        stage('build image') {
            when {
                    expression {
                        BRANCH_NAME == 'master'
                    }
                }
            steps {
                
                script {
                    gv.buildImage()
                    
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    gv.deploy()
                }
            }
        }
    }
}
