pipeline{
   agent any
   tools{
       maven '3.8.6'
   }
   stages{
      stage("source"){
          steps{
             git branch: 'master' ,url:'https://github.com/tallahmad047/projet-sir-2022-main.git'
        }
      }
       stage ('Build') {
                  steps{
                      bat 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install'
                  }
              }

              stage ('SonarQube Analysis') {
                  steps{
                      bat 'mvn sonar:sonar'
                  }
              }

              stage ('Approve Deployment') {
                  input {
                      message 'Do you want to proceed for deployment?'
                  }
                  steps{
                      bat 'echo "Deploying into Server dev."'
                  }
              }
               stage('Builddocker') {
                          steps {
                              // Build the Docker image
                              bat 'docker build -t projetsir2022/projet-sir:groupe5  .'
                          }
                      }

                      stage('Push') {
                          steps {
                              // Log in to Docker Hub
                              bat 'echo "ProjetSir2022" | docker login -u "projetsir2022" '
                              // Push the image to Docker Hub
                              bat 'docker push projet-sir:groupe5 '
                          }
                      }

          } // stages

          post {
              aborted {
                  echo "Sending message to Agent"
              } // aborted

              failure {
                  echo "Sending message to Agent"
              } // failure

              success {
                  echo "Sending message to Agent"
              } // success
          } // post
   }
