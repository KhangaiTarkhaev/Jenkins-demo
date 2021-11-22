pipeline {
    agent any
    stages {
        stage('clean workspace') {
            steps {
                cleanWs();
            }
        }
        stage('clone sources') {
            steps {
                sh 'git config --global http.sslverify false'
                git branch: 'feature/jenkins-test',
                        credentialsId: 'GitHub',
                        url: 'https://github.com/KhangaiTarkhaev/Jenkins-demo.git'
            }
        }
        stage('compile') {
            withMaven {
                sh 'mvn clean compile'
            }
        }
        stage('tests') {
            withMaven {
                sh 'mvn test'
            }
        }
        stage('build image') {
            withMaven {
                sh 'mvn docker:build'
            }
        }
        stage('sonar') {
            withMaven {
                sh 'mvn sonar:sonar'
            }
        }
        stage('deploy image') {
            withMaven {
                sh 'mvn docker:push'
            }
        }
    }

}