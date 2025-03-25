pipeline {
    agent any

    tools{
      jdk 'jdk11'
      maven 'maven3'
    }
  
    environment {
        IMAGE_NAME = 'user-role-app'
        CONTAINER_NAME = 'user-role-app-container'
        DOCKER_PORT = '8081'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/AnzZamAdl/user-role-app--crub-jpa-spring-boot.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        //stage('OWASP Dependency Check'){
           // steps{
             //   dependencyCheck additionalArguments: '--scan ./', odcInstallation: 'DP'
               // dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
            //}
            //post {
              //  always {
                //    archiveArtifacts artifacts: '**/dependency-check-report.xml', fingerprint: true
                //}
            //}
        //}

        stage('Run Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'docker-login', toolName: 'docker') {
                        sh """
                            docker build -t ${DOCKER_IMAGE} .
                            docker tag ${DOCKER_IMAGE} ${DOCKER_IMAGE}:latest
                            docker push ${DOCKER_IMAGE}:latest
                        """
                    }
                }
            }
        }
        
        stage('Deploy to Container') {
            steps {
                script {
                    sh "docker stop ${DOCKER_CONTAINER} || true"
                    sh "docker rm ${DOCKER_CONTAINER} || true"
                    withDockerRegistry(credentialsId: 'docker-login', toolName: 'docker') {
                        sh """
                            docker run -d --name ${DOCKER_CONTAINER} -p ${DOCKER_PORT}:8080 ${DOCKER_IMAGE}:latest
                        """
                    }
                }
            }
        }
    }

    post {
        success {
            echo "Build and Deployment Successful!"
        }
        failure {
            echo "Build or Deployment Failed!"
        }
    }
}
