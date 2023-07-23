// utils/websocket.ts
import router from "next/router";
import io from 'socket.io-client';

let socket: any;

interface websocketProps {
    username: string;
}

export const initiateSocket = (websocketProps: websocketProps) => {
    const token = localStorage.getItem('token');
    const validity = localStorage.getItem('validity');
    const date = localStorage.getItem('date');


    const isTokenExpired = new Date().getTime() - Number(date) > Number(validity);

    if (isTokenExpired || !token) {
        localStorage.removeItem('jwtToken');
        localStorage.removeItem('validity');
        localStorage.removeItem('date');
        router.push('/auth');
        // throw new Error('Token expired. Please log in again.');
    }
    socket = io('ws://localhost:8082?code=' + websocketProps.username + '&token=' + token, {
        transports: ['websocket'],
    });
    // console.log("ws://localhost:8082?code=" + websocketProps.username + "&token=" + token);

};

export const getSocket = () => {
    return socket;
};
