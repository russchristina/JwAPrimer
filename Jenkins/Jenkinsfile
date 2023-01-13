pipeline{
    agent any

    stages{
        stage('Build'){
            steps{
                echo 'Building after webhook added...'
                sh 'sudo sh ./mvnw clean package -DskipTests'
            }
        }

        stage('Test'){
            steps{
                echo 'Running tests...'
            }
        }

        stage('Deploy'){
            steps{
                 withEnv(['JENKINS_NODE_COOKIE=dontKillMe']){
                    echo 'Deploying app...'
                    sh 'java -jar ./target/*.jar &'
                 }
            }
        }
    }
}
