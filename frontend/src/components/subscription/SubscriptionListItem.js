/* eslint-disable react/prop-types */
import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Modal from '@material-ui/core/Modal';
import SubscriptionModalContent from './SubscriptionModalContent';

//TODO: add PropType validation

const useStyles = makeStyles(() => ({
	root: {
		minWidth: 600,
	},
	bullet: {
		display: 'inline-block',
		margin: '0 2px',
		transform: 'scale(0.8)',
	},
	title: {
		fontSize: 14,
	},
	pos: {
		marginBottom: 5,
	},
	active: {
		backgroundColor: 'white',
		color: 'black'
	},
}));

const SubscriptionListItem = ({ subscription }) => {

	const [modalIsOpen, setModalIsOpen] = useState(false);

	const { currencyThreshold, currencyType, isActive, itemTypes, name, pk, tabIds } = subscription;
	const classes = useStyles();

	const handleOpen = () => {
		setModalIsOpen(true);
	};

	const handleClose = () => {
		setModalIsOpen(false);
	};

	const body = (
		<SubscriptionModalContent data="heu" />
	);

	return (

		<Card key={pk} className={classes.root, (isActive ? classes.active : null)} variant='outlined'>
			<CardContent>
				<Typography className={classes.title} variant='h6' color='textSecondary' gutterBottom>
					{`Name: ${name ? name : ''}`}
				</Typography>
				<Typography className={classes.pos} variant='subtitle1'>
					{`Currency Threshold: ${currencyThreshold ? currencyThreshold : ''}`}
				</Typography>
				<Typography className={classes.pos} variant='subtitle1'>
					{`Currency Type: ${currencyType ? currencyType : ''}`}
				</Typography>
				<Typography className={classes.pos} variant='subtitle1'>
					{`Item Types: ${itemTypes ? itemTypes : ''}`}
				</Typography>
				<Typography className={classes.pos} variant='subtitle1'>
					{`Tab ids: ${tabIds ? tabIds : ''} `}
				</Typography>
			</CardContent>
			<CardActions >
				<Button variant='contained' color='primary' onClick={handleOpen}>
					Edit
				</Button>
				<Button variant='contained' color='secondary' onClick={handleOpen}>
					Delete
				</Button>
			</CardActions>

			<Modal
				open={modalIsOpen}
				onClose={handleClose}
				aria-labelledby="simple-modal-title"
				aria-describedby="simple-modal-description"
			>
				{body}
			</Modal>

		</Card>

	)
}



export default SubscriptionListItem;
