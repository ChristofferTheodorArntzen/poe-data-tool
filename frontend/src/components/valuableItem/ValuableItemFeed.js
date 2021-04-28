/* eslint-disable react/prop-types */
import React, { useState, useEffect, useContext } from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import ValuableItemRow from './ValuableItemRow';
import '../../styles/ValuableItemFeed.css';

import { getValuableItem, deleteValuableItem } from '../../adapters/ValuableItemAdapter';
import { connectToEndpoint, socketCallBack } from '../../adapters/ValuableItemWSAdapter';
import { connectionContext } from "../../contexts/ConnectionContext";


const errorStyles = {
    color: 'white',
    textAlign: 'center',
    fontSize: '20px',
    padding: '20px'
}

const ValuableItemFeed = ({ webSocketTopic }) => {

    const [valuableItemArray, setValuableItemArray] = useState([]);
    const [hasError, setError] = useState(false);

    const { isConnected } = useContext(connectionContext);

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

    useEffect( () => {
        if(!isConnected) {
            setError(true);
        } else {
            setError(false);
        }
    }, [isConnected])

    useEffect( () => {
        fetchData();
    }, []);

    // --- Socket connection
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
                client.subscribe(webSocketTopic,
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

    const constructRowComponents = (items) => {
        const rows = items.map((item) => {
            return (
                <ValuableItemRow
                key={item.id}
                item={item}
                handleClick={handleClick}
                />
            );
        })

        return rows;
    }

    const errorDisplay = (
        <div style={errorStyles}>
            <label>Could not connect to the server. Try to relaunch the application.</label>
        </div>
    )

    //TODO: add css from Valuable item pertaining to style here, keep positional css in the css file.
    const tableComponent = (
        <TableContainer component={Paper} className='tableFixHead'>
            <Table aria-label='simple table'>
                <TableHead>
                    <TableRow>
                        <TableCell align='center'>Img</TableCell>
                        <TableCell align='center'>Name</TableCell>
                        <TableCell align='center'>Type</TableCell>
                        <TableCell align='center'>Stash ID</TableCell>
                        <TableCell align='center'>Price</TableCell>
                        <TableCell align='center'>Price Type</TableCell>
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