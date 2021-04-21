import { makeStyles } from '@material-ui/core';
import React from 'react';


const useStyles = makeStyles((theme) => ({
    paper: {
		color: 'white',
		position: 'absolute',
		width: 400,
		backgroundColor: 'rgb(71, 71, 71)',
		border: '2px solid #FFF',
		boxShadow: theme.shadows[5],
		padding: theme.spacing(2, 4, 3),
		margin: '0 auto',
		left: '50%',
		top: '50%',
		transform: 'translate(-50%, -50%)',
	},
}));

const SubscriptionModalContent = (data) => {

    
    console.log('modal prop');
    console.log(data);

    const classes = useStyles();

    return (
        <div className={classes.paper}>
            <h2 id="simple-modal-title">Text in a modal</h2>
            <p id="simple-modal-description">
                Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
        </p>
        </div>
    );
}
export default SubscriptionModalContent;