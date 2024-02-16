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
                    gv = load "scripts.groovy"
                }

            }
        }
        stage('test') {
            steps {

                script {
                    gv.buildjar()
                }

            }
        }
        stage('build jar') {
            steps {
                when {
                    expression {
                        BRANCH_NAME == 'master'
                    }
                }

                script {
                    gv.buildjar()
                }

            }
        }
        stage('build image') {
            steps {
                when {
                    expression {
                        BRANCH_NAME == 'master'
                    }
                }
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
