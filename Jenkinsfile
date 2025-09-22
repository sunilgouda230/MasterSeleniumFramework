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
               // Optional: archive allure results for backup
               archiveArtifacts artifacts: 'allure-results/**', fingerprint: true
           }
       }

    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
            allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
        }
    }


}
