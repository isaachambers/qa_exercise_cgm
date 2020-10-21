pipeline {
    agent { dockerfile true }
    stages {
        stage('Build & Test') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew build'
                sh './gradlew test jacocoTestReport'
            }
        }
    }
}