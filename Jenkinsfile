pipeline {
    agent any

    stages {

        // Docker 이미지 빌드 및 배포 단계
        stage('Build and Deploy Docker') {
            when {
                expression { currentBuild.resultIsBetterOrEqualTo('UNSTABLE') }
            }
            steps {
                script {
                    // backend 디렉토리로 이동하고 Docker 이미지 빌드
                    dir('/var/lib/jenkins/workspace/nadeuli/backend/') {
                        sh 'sudo docker build -t lsm00/nadeuliWas:latest .'
                    }

                    // 이전에 실행 중이던 도커 컨테이너 중지 및 삭제
                    def existingContainerId = sh(script: 'docker ps -aq --filter name=nadeuliWas', returnStdout: true).trim()
                    if (existingContainerId) {
                        sh "docker stop $existingContainerId"
                        sh "docker rm $existingContainerId"
                    }

                    // 새로운 도커 컨테이너 실행
                    sh 'docker run -d -p 80:80 -dit --name nadeuliWas lsm00/nadeuliWas:latest'

                    withCredentials([string(credentialsId: 'docker_hub_access_token', variable: 'DOCKERHUB_ACCESS_TOKEN')]) {
                        // Docker Hub에 로그인하고 이미지 푸시
                        sh "docker login -u lsm00 -p $DOCKERHUB_ACCESS_TOKEN"
                        sh "docker push lsm00/nadeuliWas:latest"
                    }

                    // 빌드 후에 로컬 이미지 삭제
                    sh 'sudo docker rmi -f $(sudo docker images -q -f "dangling=true")'
                }
            }
        }
        stage("Slack Notification") {
            when {
                expression { currentBuild.resultIsBetterOrEqualTo('UNSTABLE') }
            }
            steps {
                echo 'Slack 통지 테스트'
            }
            post {
                success {
                    slackSend channel: '#jenkins', color: 'good', message: "Was 배포 성공"
                }
                failure {
                    slackSend channel: '#jenkins', color: 'danger', message: "Was 배포 실패"
                }
            }
        }
    }
}
