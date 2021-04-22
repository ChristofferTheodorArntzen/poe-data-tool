/* eslint-disable react/prop-types */
import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogContent from '@material-ui/core/DialogContent';
import SubscriptionForm from './SubscriptionForm';

const SubscriptionFormDialog = ({ subscription }) => {

    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <div>
            <Button variant='contained' color='primary' onClick={handleClickOpen}>
               { (subscription) ? 'Edit' : 'Add'}
            </Button>
            <Dialog open={open} onClose={handleClose}>
                <DialogContent>
                    <SubscriptionForm close={handleClose} subscriptionAsProp={subscription} />
                </DialogContent>
            </Dialog>
        </div>
    );
}

export default SubscriptionFormDialog;