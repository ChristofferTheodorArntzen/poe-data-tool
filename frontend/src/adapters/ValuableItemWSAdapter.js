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
    stompClient.debug = null;

    return stompClient;
}

export function socketCallBack(msg) {
    const json = JSON.parse(msg.body);
    
    let valuableItem = {
        id: json.id,
        name: json.item.name,
        type: json.item.typeLine,
        stashId: json.item.inventoryId,
        svg: json.item.icon,
        price: {
            mean: json.mean,
            median: json.median,
            max: json.max,
            min: json.min,
        },
        priceType: 'chaos', // TODO - this data is available on the subscription, not on each item, maybe just add it there too.
    }

    return valuableItem;
}