pipeline {
    agent any

    tools {
        jdk 'JDK17'       // Name from Jenkins Global Tool Config
        maven 'Maven3'    // Name from Jenkins Global Tool Config
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

        stage('Generate Allure Report') {
            steps {
                // Generate Allure HTML report
                sh 'allure generate allure-results -o allure-report --clean || true'

                // Archive results for Jenkins UI
                archiveArtifacts artifacts: 'allure-results/**, allure-report/**', fingerprint: true
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'

            // Use correct path to results
            allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
        }
    }

}
