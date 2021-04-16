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
import '../../styles/ValuableItemFeed.css';

import { getValuableItem, deleteValuableItem } from '../../adapters/ValuableItemAdapter';
import { connectToEndpoint, socketCallBack, webSocketSubscribePoint } from '../../adapters/SocketAdapter';

const useStyles = makeStyles({
    table: {
        minWidth: 650,
        maxWidth: 1000,
        overflowY: 'auto',
    },
});

const errorStyles = {
    color: 'white',
    textAlign: 'center',
    fontSize: '20px',
    padding: '20px'
}

const ValuableItemFeed = () => {

    const [valuableItemArray, setValuableItemArray] = useState([]);
    const [hasError, setError] = useState(false);

    // Fetches already created valuableItems
    async function fetchData() {
        try {
            const fetchedItems = await getValuableItem();
            if(fetchedItems == null) {
                setError(true);
            } else {
                setValuableItemArray(fetchedItems);
            }
    
        } catch (err) {
            console.log(err);
        }
    }

    // adding a useEffect to fetchData when it is mounted.
    useEffect( async () => {
        fetchData();
    }, []);

    // --- Socket connection - TODO:
    function updateStateWithSocketCallBack(msg) {
        let parsedItem = socketCallBack(msg);
        if (parsedItem == null) return;

        setValuableItemArray((value) => {
            return [parsedItem, ...value]
        });

    }

    useEffect(() => {
        
        try {
            const client = connectToEndpoint();
            client.connect({}, () => {
                client.subscribe(webSocketSubscribePoint,
                    (msg) => updateStateWithSocketCallBack(msg))
            });
    
            return () => {
                if(client != null && client.connected) client.disconnect()
            }   
        } catch (err) {
            setError(true);
            console.log(err);
        }

    }, []);
    // --- Socket connection

    const handleClick = (itemId) => {
        deleteValuableItem(itemId).then(resp => {
            if (resp.status !== 200) return;
            setValuableItemArray((value) => {
                return value.filter((item) => item.id !== itemId);
            })
        });
    }

    //TODO: should this be gotten from a context or something similar? this takes 1 - 1.5 sec to retrieve and "make" when there is lots of data. 
    //maybe serve a 1:1 dto from API to remove the mapping that is done. 
    //TODO: make this its own functional component way to much code here
    const constructRowComponents = (items) => {
        const rows = items.map((item) => {
            return (
                <TableRow key={'table-row' + item.id}>
                    <TableCell component='th' scope='row'>
                        <img loading='lazy' src={item.svg} className='item-frame' />
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
        })

        return rows;
    }

    const errorDisplay = (
        <div style={errorStyles}>
            <label>Could not connect to the server. Try to relaunch the application.</label>
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
                    {(valuableItemArray.length == 0) ? null : constructRowComponents(valuableItemArray)}
                </TableBody>
            </Table>
        </TableContainer>
    )
    
    return (
        <main>
            <div className='table-container'>
                {(hasError) ? errorDisplay : tableComponent}
            </div>
        </main>
    )
}

export default ValuableItemFeed;