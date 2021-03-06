/* eslint-disable react/prop-types */
import React, { useState, useEffect, useContext } from 'react';
import Snackbar from '@material-ui/core/Snackbar';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';

import { connectionContext } from "../../contexts/ConnectionContext";

const notConnectedText = 'Not Connected'
const connectedText = 'Reconnected'

//TODO: add props and make more generic. Take in context object

const Notification = () => {

    const [open, setOpen] = useState(false);
    const { isConnected } = useContext(connectionContext);

    useEffect(() => {
        setOpen(true);
    }, [isConnected]);


    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }

        setOpen(false);
    };

    return (
        <div>
            <Snackbar
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'left',
                }}
                open={open}
                autoHideDuration={3000}
                onClose={handleClose}
                message={(isConnected) ? connectedText : notConnectedText}
                action={
                    <React.Fragment>
                        <IconButton size="small" aria-label="close" color="inherit" onClick={handleClose}>
                            <CloseIcon fontSize="small" />
                        </IconButton>
                    </React.Fragment>
                }
            />
        </div>
    )

}

export default Notification;