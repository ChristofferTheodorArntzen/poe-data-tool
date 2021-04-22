import SockJS from 'sockjs-client'
import Stomp from 'stomp-websocket';

const socketEndPoint = 'http://localhost:8080/valuableItemFeed';
export const topicFeed = '/feed';
export const topicGenerator = '/generator';

function returnWebSocket() {
    const socket = new SockJS(socketEndPoint);
    const stompClient = Stomp.over(socket);
    return stompClient;
}

export function connectToEndpoint() {

    const stompClient = returnWebSocket();
    // stompClient.debug = null;

    return stompClient;
}

export function socketCallBack(msg) {
    const receivedJson = JSON.parse(msg.body);
    const item = receivedJson.item;
    return {
        id: item.id,
        name: item.name,
        type: item.typeLine,
        stashId: item.inventoryId,
        svg: item.icon,
        price: 10000,
        priceType: 'chaos',
    };
}