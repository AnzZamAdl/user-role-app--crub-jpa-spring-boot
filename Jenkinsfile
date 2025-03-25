pipeline {
    agent any

    tools{
      jdk 'jdk11'
      maven 'maven3'
    }
  
    environment {
        IMAGE_NAME = 'anzzamadl/user-role-app'
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
                sh 'ls -lah target/'  // Debugging step
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
                            docker build -t ${IMAGE_NAME} .
                            docker tag ${IMAGE_NAME} ${IMAGE_NAME}:latest
                            docker push ${IMAGE_NAME}:latest
                        """
                    }
                }
            }
        }
        
        stage('Deploy to Container') {
            steps {
                script {
                    sh "docker stop ${CONTAINER_NAME} || true"
                    sh "docker rm ${CONTAINER_NAME} || true"
                    withDockerRegistry(credentialsId: 'docker-login', toolName: 'docker') {
                        sh """
                            docker run -d --name ${CONTAINER_NAME} -p ${DOCKER_PORT}:8080 ${IMAGE_NAME}:latest
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
