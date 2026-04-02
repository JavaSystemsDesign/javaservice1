pipeline {
    agent any

    tools {
        maven 'Maven 3'
        jdk 'Java 17'
    }

    environment {
        APP_NAME    = 'javaservice1'
        MAVEN_OPTS  = '-Xmx512m -XX:+TieredCompilation'
    }

    options {
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
        timestamps()
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
                echo "Repository checked out – branch: ${env.BRANCH_NAME ?: 'detached HEAD'}"
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile -B'
            }
            post {
                failure {
                    echo 'Build stage failed – check compilation errors above.'
                }
            }
        }

        stage('Test') {
            parallel {

                stage('Unit Tests – Controllers') {
                    steps {
                        sh '''
                            mvn test -B \
                                -Dtest="com.example.javaservice1.controller.*" \
                                -DfailIfNoTests=false \
                                -Dsurefire.reportsDirectory=target/surefire-reports-controllers
                        '''
                    }
                    post {
                        always {
                            junit allowEmptyResults: true,
                                  testResults: 'target/surefire-reports-controllers/TEST-*.xml'
                        }
                        failure {
                            echo 'Controller unit tests failed.'
                        }
                    }
                }

                stage('Unit Tests – Services') {
                    steps {
                        sh '''
                            mvn test -B \
                                -Dtest="com.example.javaservice1.service.*" \
                                -DfailIfNoTests=false \
                                -Dsurefire.reportsDirectory=target/surefire-reports-services
                        '''
                    }
                    post {
                        always {
                            junit allowEmptyResults: true,
                                  testResults: 'target/surefire-reports-services/TEST-*.xml'
                        }
                        failure {
                            echo 'Service unit tests failed.'
                        }
                    }
                }

            }
        }

        stage('Package') {
            steps {
                sh 'mvn install -B -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar',
                                     fingerprint: true,
                                     allowEmptyArchive: false
                    echo "Artifact archived: target/${APP_NAME}-*.jar"
                }
                failure {
                    echo 'Package stage failed – artifact was not produced.'
                }
            }
        }

    }

    post {
        always {
            echo "Pipeline finished with status: ${currentBuild.currentResult}"
            cleanWs()
        }
        success {
            echo "SUCCESS – ${APP_NAME} build #${env.BUILD_NUMBER} completed."
        }
        failure {
            echo "FAILURE – ${APP_NAME} build #${env.BUILD_NUMBER} failed. Review the logs above."
        }
        unstable {
            echo "UNSTABLE – ${APP_NAME} build #${env.BUILD_NUMBER} has test failures."
        }
    }
}
