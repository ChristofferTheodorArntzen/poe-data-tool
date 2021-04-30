import { get, deleteRequest } from './BaseAdapter';

const devUrl = 'http://localhost:8080/valuableItem/';

//TODO: only return the last 20 or 50? or impl. lazy loading
export async function getValuableItem() {

    try {
        const response = await get(devUrl);

        if (response.status != 200) {
            return null;
        }
        
        const valuableItemArray = response.data.map((jsonItem) => {
            let valuableItem = {
                id: jsonItem.id,
                item: jsonItem.item,
                price: {
                    mean: jsonItem.mean,
                    median: jsonItem.median,
                    max: jsonItem.max,
                    min: jsonItem.min,
                },
                priceType: 'chaos', // TODO - this data is available on the subscription, not on each item, maybe just add it there too.
            }
            return valuableItem;
        });

        return valuableItemArray;
    } catch (err) {
        console.log(err);
    }
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
