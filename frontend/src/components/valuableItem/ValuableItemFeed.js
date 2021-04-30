/* eslint-disable react/prop-types */
import React, { useState, useEffect, useContext } from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import ValuableItemRow from './ValuableItemRow';
import { makeStyles } from '@material-ui/core';
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

const useStyles = makeStyles({
    table: {
        overflow: 'auto',
        height: '100%',
        backgroundColor: 'rgba(30, 30, 30, 0.96)'
    },
    tableHead: {
        backgroundColor: 'rgba(90, 90, 90, 0.5)'
    }
})

const ValuableItemFeed = ({ webSocketTopic }) => {

    const [valuableItemArray, setValuableItemArray] = useState([]);
    const [hasError, setError] = useState(false);

    const { isConnected } = useContext(connectionContext);

    const classes = useStyles();

    async function fetchData() {
        try {
            const fetchedItems = await getValuableItem();
            if (fetchedItems == null) {
                setError(true);
            } else {
                setValuableItemArray(fetchedItems);
            }

        } catch (err) {
            console.log(err);
        }
    }

    useEffect(() => {
        if (!isConnected) {
            setError(true);
        } else {
            setError(false);
        }
    }, [isConnected])

    useEffect(() => {
        fetchData();
    }, []);

    // --- Socket connection
    function updateStateWithSocketCallBack(msg) {
        let parsedItem = socketCallBack(msg);
        if (parsedItem == null) return;

        setValuableItemArray((value) => ([parsedItem, ...value]));
    }

    useEffect(() => {
        try {
            const client = connectToEndpoint();
            client.connect({}, () => {
                client.subscribe(webSocketTopic,
                    (msg) => updateStateWithSocketCallBack(msg))
            });

            return () => {
                if (client != null && client.connected) client.disconnect()
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

    const errorDisplay = (
        <div style={errorStyles}>
            <label>Could not connect to the server. Try to relaunch the application.</label>
        </div>
    )
    const tableComponent = (
        <TableContainer className={classes.table} >
            <Table aria-label='simple table'>
                <TableHead className={classes.tableHead}>
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
                    {
                        (valuableItemArray.length != 0) ? (
                            valuableItemArray.map((row) =>
                                <ValuableItemRow key={row.id}
                                    valuableItem={row}
                                    handleClick={handleClick} />
                            )
                        ) : null
                    }
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