pipeline {
    agent any

    tools {
        jdk 'JDK17'       // Make sure this matches Jenkins Global Tool Config
        maven 'Maven'     // Change this to the exact name in Jenkins Global Tool Config
    }

    environment {
        SELENIUM_GRID_URL = 'http://localhost:4444/wd/hub'
    }

    stages {
        stage('Checkout') {
            steps {
                // Pull code from GitHub
                git credentialsId: 'git-creds',
                    url: 'https://github.com/sunilgouda230/MasterSeleniumFramework.git',
                    branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Regression Tests') {
            steps {
                sh "mvn test -Dselenium.grid.url=${SELENIUM_GRID_URL}"
            }
        }

        stage('Archive Allure Results') {
            steps {
                // Archive allure-results folder
                archiveArtifacts artifacts: 'allure-results/**', fingerprint: true
            }
        }
    }

    post {
        always {
            // Publish JUnit results
            junit 'target/surefire-reports/*.xml'

            // Publish Allure report using plugin (no manual allure CLI needed)
            allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
        }
    }
}
