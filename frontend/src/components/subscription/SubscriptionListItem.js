/* eslint-disable no-unused-vars */
/* eslint-disable react/prop-types */
import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

import SubscriptionFormDialog from './SubscriptionFormDialog';
import { List, ListItem } from '@material-ui/core';

//TODO: add PropType validation

const useStyles = makeStyles(() => ({
	root: {
		minWidth: 600,
	},
	title: {
		fontSize: 14,
	},
	item: {
		marginBottom: 5,
		wordBreak: 'break-all',
	},
	active: {
		backgroundColor: '#808080',
	},
	listItem: {
		width: '90%',
		padding: 0,
		margin: '3px 1rem',
		backgroundColor: '#5c5c5c'
	},
}));

const SubscriptionListItem = ({ subscription, handleSetActive, handleDelete }) => {

	const { currencyThreshold, currencyType, isActive, itemTypes, name, pk, tabIds } = subscription;
	const classes = useStyles();

	return (
		<Card key={pk} className={classes.root, (isActive ? classes.active : null)} variant='outlined'>
			<CardContent>
				<Typography className={classes.title} variant='h6' color='textSecondary' gutterBottom>
					{`Name: ${name ? name : ''}`}
				</Typography>
				<Typography className={classes.item} variant='subtitle1'>
					{`Currency Threshold: ${currencyThreshold ? currencyThreshold : ''}`}
				</Typography>
				<Typography className={classes.item} variant='subtitle1'>
					{`Currency Type: ${currencyType ? currencyType : ''}`}
				</Typography>

				<Typography className={classes.item} variant='subtitle1'>
					Item Types:
				</Typography>
				<List>
					{itemTypes.map((itemType) => {
						return (
							<ListItem key={itemType} className={classes.listItem}>
								<Typography className={classes.listItem} variant='subtitle1'>
									{itemType}
								</Typography>
							</ListItem>
						)
					})}
				</List>

				<Typography className={classes.item} variant='subtitle1'>
					{`Tab ids: ${tabIds ? tabIds : ''} `}
				</Typography>
			</CardContent>
			<CardActions>

				<Button variant="contained" onClick={() => handleSetActive(subscription)}>
					Set Active
				</Button>

				<SubscriptionFormDialog subscription={subscription} />

				<Button variant='contained' color='secondary' onClick={() => handleDelete(subscription)}>
					Delete
				</Button>

			</CardActions>


		</Card>

	)
}

export default SubscriptionListItem;
