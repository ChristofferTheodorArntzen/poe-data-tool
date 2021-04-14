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

import { getValuableItem, deleteValuableItem } from '../../adapters/ValuableItemAdapter';
import { connectToEndpoint, socketCallBack, webSocketSubscribePoint } from '../../adapters/SocketAdapter';

const useStyles = makeStyles({
    table: {
        minWidth: 650,
        maxWidth: 1000,
        overflowY: 'auto',
    },
});

const ValuableItemFeed = () => {

    const [valuableItem, setValuableItem] = useState([]);
    const [hasError, setErrors] = useState(false);
    // const [connection, setConnection] = useState();
    // const [isConnected, setIsConnected] = useState();

    // Fetches already created valuableItems
    async function fetchData() {
        getValuableItem().then(data => setValuableItem(data)).catch(err => setErrors(err));
    }

    useEffect(() => {
        fetchData();
    }, []);

    function updateStateWithSocketCallBack(msg) {
        let parsedItem = socketCallBack(msg);
        
        setValuableItem((value) =>  {
            return [parsedItem, ...value]
        });

    }

    useEffect(() => {
        const client = connectToEndpoint();
        client.connect({}, () => {
            client.subscribe(webSocketSubscribePoint,
                (msg) => updateStateWithSocketCallBack(msg))
        });

        return () => {
            client.disconnect();
        }
    }, []);

    // Calls backend tries to delete there, if 200 OK then removes from UI.
    const handleClick = (itemId) => {
        deleteValuableItem(itemId).then(resp => {
            if (resp.status !== 200) return;
            setValuableItem((value) => {
                return value.filter((item) => item.id !== itemId);
            })
        });
    }

    //TODO: should this be gotten from a context or something similar? this takes 1 - 1.5 sec to retrieve and "make"
    //TODO: make this its own functional component way to much code here
    const constructedRowComponent = valuableItem.map((item) => {

        return (
            <TableRow key={'table-row' + item.id}>
                <TableCell component='th' scope='row'>
                    <img src={item.svg} className='item-frame' />
                </TableCell>
                <TableCell>{item.name}</TableCell>
                <TableCell>{item.type}</TableCell>
                <TableCell>{item.stashId}</TableCell>
                <TableCell>{item.price.median}</TableCell>
                <TableCell>{item.priceType}</TableCell>
                <TableCell>
                    <Button onClick={() => handleClick(item.id)}
                        variant='contained'
                        color='secondary'
                        size='small'
                        startIcon={<DeleteIcon />}
                    >
                        Delete
                    </Button>
                </TableCell>
            </TableRow>
        );

    });

    const loadingDisplay = (
        <div>
            <label>Loading...</label>
        </div>
    )

    const tableComponent = (
        <TableContainer component={Paper}>
            <Table className={useStyles.table} aria-label='simple table'>
                <TableHead>
                    <TableRow>
                        <TableCell align='center'>Img</TableCell>
                        <TableCell align='center'>Name</TableCell>
                        <TableCell align='center'>Type</TableCell>
                        <TableCell align='center'>Stash ID</TableCell>
                        <TableCell align='center'>Price</TableCell>
                        <TableCell align='center'>PriceType</TableCell>
                        <TableCell align='center'>Remove</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {constructedRowComponent}
                </TableBody>
            </Table>
        </TableContainer>
    )
    
    console.log(hasError);

    return (
        <main>
            <div className='table-container'>
                {(constructedRowComponent.length > 0) ? tableComponent : loadingDisplay}
            </div>
        </main>
    )
}

export default ValuableItemFeed;