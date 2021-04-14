/* eslint-disable no-unused-labels */
/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */

import React, { useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import DeleteIcon from '@material-ui/icons/Delete';
import './ValuableItemContainer.css';
import SockJS from 'sockjs-client';
import Stomp from 'stomp-websocket';


const useStyles = makeStyles({
    table: {
        minWidth: 650,
        maxWidth: 1000,
        overflowY: 'auto',
    },
});

const connect = () => {
    const sock = new SockJS('http://localhost:8080/gs-guide-websocket');


}

const fetchData = async () => {

    // fetching items from backend api
    const url = 'http://localhost:8080/valuableItem';

    const response = await fetch(url);
    const json = await response.json();

    const fetchItems = json.map( (jsonItem) => {
        let valuableItem = {
            id: jsonItem.id,
            name: jsonItem.item.name,
            type: jsonItem.item.typeLine,
            stashId: jsonItem.item.inventoryId,
            svg: jsonItem.item.icon,
            price: {
                mean: jsonItem.mean,
                median: jsonItem.median,
                max: jsonItem.max,
                min: jsonItem.min,
            },
            priceType: 

        }

    })

    // const fetchedItems = json.map((valuableItem) => {
    //     let renderableItem = {
    //         id: valuableItem.id,
    //         name: valuableItem.item.name,
    //         type: valuableItem.item.typeLine,
    //         stashId: valuableItem.item.inventoryId,
    //         svg: valuableItem.item.icon,
    //         price: valuableItem.estimatedPrice,
    //         priceType: 'chaos',
    //     };

    //     return renderableItem;
    // }
}

const ValuableItemFeed = () => {

    const [valuableItem, setValuableItem] = useState();
    const [connection, setConnection] = useState();
    const [isConnected, setIsConnected] = useState();

    const data = fetchData();

    console.log(data);

    useEffect(() => {

    });


    return (
        <div className='table-container'>
            hei hei hei dette funker fortsatt
        </div>
    )
}

export default ValuableItemFeed;






// 		this.handleClick = this.handleClick.bind(this);
// 		this.wsClient = null;


// 	async componentDidMount() {
// 		const url = 'http://localhost:8080/valuableItem';

// 		const response = await fetch(url);
// 		const json = await response.json();

// 		const fetchedItems = json.map((valuableItem) => {
// 			let renderableItem = {
// 				id: valuableItem.id,
// 				name: valuableItem.item.name,
// 				type: valuableItem.item.typeLine,
// 				stashId: valuableItem.item.inventoryId,
// 				svg: valuableItem.item.icon,
// 				price: valuableItem.estimatedPrice,
// 				priceType: 'chaos',
// 			};

// 			return renderableItem;
// 		});

// 		this.setState({
// 			renderableItems: fetchedItems,
// 		});

// 		const sock = new SockJS('http://localhost:8080/gs-guide-websocket');
// 		const stompClient = Stomp.over(sock);
// 		stompClient.debug = null;

// 		this.wsClient = stompClient;

// 		stompClient.connect({}, () => {
// 			stompClient.subscribe('/topic/greetings', (msg) =>
// 				this.subscriptionCallBack(msg)
// 			);
// 		});
// 	}

// 	subscriptionCallBack(msg) {

// 		const receivedJson = JSON.parse(msg.body);

// 		const item = receivedJson.item;

// 		console.log(item);

// 		const newItem = {
// 			id: item.id,
// 			name: item.name,
// 			type: item.typeLine,
// 			stashId: item.inventoryId,
// 			svg: item.icon,
// 			price: 2,
// 			priceType: 'chaos',
// 		};

// 		this.setState({
// 			renderableItems: [newItem, ...this.state.renderableItems],
// 		});
// 	}

// 	async handleClick(id) {
// 		const url = `http://localhost:8080/valuableItem/${id}`;

// 		await fetch(url, {
// 			method: 'DELETE',
// 		}).then((response) => {
// 			if (response.status == 200) {
// 				let resultList = this.state.renderableItems.filter(
// 					(item) => item.id !== id
// 				);

// 				this.setState({
// 					renderableItems: resultList,
// 				});
// 			}
// 		});
// 	}

// 	componentWillUnmount() {
// 		this.wsClient != null 
// 		? this.wsClient.disconnect() :
// 		console.log('ws client was null');  
// 		console.log('component did unmount');
// 	}

// 	render() {
// 		const useStyles = makeStyles({
// 			table: {
// 				minWidth: 650,
// 				maxWidth: 1000,
// 				overflowY: 'auto',
// 			},
// 		});

// 		// redo the return statement... maybe make it its own class passing
// 		// state here as props

// 		return (
// 			<main>
// 				<div className='table-container'>
// 					<TableContainer component={Paper}>
// 						<Table className={useStyles.table} aria-label='simple table'>
// 							<TableHead>
// 								<TableRow>
// 									<TableCell>Img</TableCell>
// 									<TableCell algin='right'>Name</TableCell>
// 									<TableCell algin='right'>Type</TableCell>
// 									<TableCell algin='right'>Stash ID</TableCell>
// 									<TableCell algin='right'>Price</TableCell>
// 									<TableCell algin='right'>PriceType</TableCell>
// 									<TableCell align='right'></TableCell>
// 								</TableRow>
// 							</TableHead>
// 							<TableBody>
// 								{this.state.renderableItems.map((item) => (
// 									<TableRow key={item.id}>
// 										<TableCell component='th' scope='row'>
// 											<img src={item.svg} className='item-frame'></img>
// 										</TableCell>
// 										<TableCell>{item.name}</TableCell>
// 										<TableCell>{item.type}</TableCell>
// 										<TableCell>{item.stashId}</TableCell>
// 										<TableCell>{item.price}</TableCell>
// 										<TableCell>{item.priceType}</TableCell>
// 										<TableCell>
// 											<Button
// 												onClick={() => this.handleClick(item.id)}
// 												variant='contained'
// 												color='secondary'
// 												size='small'
// 												startIcon={<DeleteIcon />}
// 											>
// 												Delete
// 											</Button>
// 										</TableCell>
// 									</TableRow>
// 								))}
// 							</TableBody>
// 						</Table>
// 					</TableContainer>
// 				</div>
// 			</main>
// 		);
// 	}
//  }
// export default ValuableItemContainer;
