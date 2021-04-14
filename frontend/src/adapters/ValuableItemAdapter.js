import { get, deleteRequest } from './BaseAdapter';

//const baseUrl = "";
const devUrl = "http://localhost:8080/valuableItem/";

//TODO: only return the last 20 or 50? or impl. lazy loading
//TODO: figure out if it is possible to just return the data instead of a promise...
export function getValuableItem() {

    return get(devUrl)
        .then(resp => {
            return resp.data.map((json) => {
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
            });
        }).catch(err => console.log(err));
}

export function deleteValuableItem(itemId) {

    let request = {
        id: itemId,
    }

    return deleteRequest(devUrl + itemId, request)
        .then(resp => {
            return resp;
        })
        .catch(resp => console.log(resp));
}
