pipeline {
    agent any

    tools {
        jdk 'JDK17'           // Jenkins global JDK name
        maven 'Maven3'        // Jenkins global Maven name
    }

    environment {
        SELENIUM_GRID_URL = 'http://localhost:4444/wd/hub'
        GRID_DIR = '/Users/sunilkumargouda/selenium-grid' // folder where docker-compose.yml is
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'git',
                    url: 'https://github.com/sunilgouda230/MasterSeleniumFramework.git',
                    branch: 'main'
            }
        }

        stage('Start Selenium Grid') {
            steps {
                dir("${GRID_DIR}") {
                    sh 'docker-compose up -d'
                    sh 'docker ps' // optional: verify grid nodes
                }
            }
        }

        stage('Build & Run Tests') {
            steps {
                // Maven automatically uses global Maven configured in Jenkins
                sh "mvn clean test -Dselenium.grid.url=${SELENIUM_GRID_URL}"
            }
        }

        stage('Archive Allure Results') {
            steps {
                archiveArtifacts artifacts: 'allure-results/**', fingerprint: true
            }
        }
    }

    post {
        always {
            stage('Stop Selenium Grid') {
                steps {
                    dir("${GRID_DIR}") {
                        sh 'docker-compose down'
                    }
                }
            }

            // Publish JUnit results
            junit 'target/surefire-reports/*.xml'

            // Publish Allure report
            allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
        }
    }
}
