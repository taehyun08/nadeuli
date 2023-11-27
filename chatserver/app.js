const express = require('express');
const http = require('http');
const mysql = require('mysql');
const dbconfig = require('./config/db_config');
const socketIo = require('socket.io');

const connection = mysql.createConnection({
  host : 'localhost',
  user : 'root',
  password : 'qkwlek12!',
  database : 'miniproject'
});
const app = express();
const server = http.createServer(app);
const io = socketIo(server);

connection.connect((err)=>{
  if(err){
    console.error(err.stack);
    return;
  }
  console.log(connection.threadId);
});


// 유저들의 소켓을 저장할 맵
const users = new Map();

io.on('connection', (socket) => {
  console.log('a user connected');

  socket.on('private message', ({ sender, receiver, message }) => {
    const receiverSocketId = users.get(receiver);
    if (receiverSocketId) {
      io.to(receiverSocketId).emit('private message', {
        sender: sender,
        message,
      });
    }
  });

  socket.on('join-room', (roomId) => {
    socket.join(roomId);
    console.log(roomId, "에 참가하였습니다.");
  });

  socket.on('disconnect', () => {
    console.log('user disconnected');
    // 유저 제거
    users.forEach((value, key) => {
      if (value === socket.id) {
        connection.end();
        users.delete(key);
      }
    });
  });
});

server.listen(3000, () => {
  console.log('Server is running on port 3000');
});

