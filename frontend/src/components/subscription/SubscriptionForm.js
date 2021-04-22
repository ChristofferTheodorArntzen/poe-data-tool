/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import React, { useState, useEffect, useContext } from 'react'
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';

import { submitSubscription } from './../../adapters/SubscriptionAdapter';
import { subscriptionContext } from './../../contexts/SubscriptionContext';

const subscriptionData = {
	pk: '',
	name: '',
	tabIds: '',
	currencyThreshold: '',
	currencyType: '',
	itemTypes: '',
	isActive: false,
}

const SubscriptionForm = (props) => {

	const { close, subscriptionAsProp } = props;
	const [subscription, setSubscription] = useState(subscriptionData);

	const { setSubscriptions } = useContext(subscriptionContext);

	const handleChange = (event) => {
		const { target: { name, value } } = event;
		setSubscription((subscription) => ({
			...subscription,
			[name]: value, event: event,
		}));
	}

	//Setting the initial state if the subscription is present
	useEffect(() => {
		if (subscriptionAsProp) {
			setSubscription(() => ({
				pk: subscriptionAsProp.pk,
				name: subscriptionAsProp.name,
				tabIds: subscriptionAsProp.tabIds[0],
				currencyThreshold: subscriptionAsProp.currencyThreshold,
				currencyType: subscriptionAsProp.currencyType,
				itemTypes: subscriptionAsProp.itemTypes[0],
				isActive: false
			}));
		}
	}, [])

	const handleAsyncSubmit = async () => {

		const responseData = await submitSubscription(subscription);
		return responseData;
	}

	//TODO: last minute fix for this to work before demo. Need to actually call setSubscriptions from context and prevent default
	const setContextSubscriptions = async (newSub) => {

		console.log('new Sub');
		console.log(newSub);

		setSubscriptions( (prevSubs) => {

			console.log('prevSub before set subs');
			console.log(prevSubs);

			let alteredSubs = prevSubs.map((sub) => {
				if(sub.pk == newSub.pk) {
					sub = newSub;
				}
			})

			console.log('prevSub after set subs');
			console.log(prevSubs);

			return alteredSubs;
		})
	}

	const handleSubmit = async (e) => {
		//TODO: last minute fix for this to work before demo. Need to actually call setSubscriptions from context and prevent default
		//e.preventDefault();

		const response =  await handleAsyncSubmit();

		try {
			//setContextSubscriptions(response);
		} catch (err) {
			console.log(err);
		} 

		close();
	}

	return (
		<form onSubmit={handleSubmit}>
			<TextField
				autoFocus
				margin='dense'
				id='name'
				label='Subscription Name'
				type='text'
				name='name'
				fullWidth
				value={subscription.name}
				onChange={handleChange}
			/>

			{/* TODO: needs to be a selectable list - See Material UI Chip for intended use */}
			<TextField
				margin='dense'
				id='tabIds'
				label='Tab Ids'
				type='text'
				name='tabIds'
				fullWidth
				value={subscription.tabIds}
				onChange={handleChange}
			/>

			<TextField
				margin='dense'
				id='currencyThreshold'
				label='Currency Threshold'
				type='number'
				name='currencyThreshold'
				fullWidth
				value={subscription.currencyThreshold}
				onChange={handleChange}
			/>

			{/* TODO: needs to be a selectable list - See Material UI Chip for intended use */}
			<TextField
				autoFocus
				margin='dense'
				id='currencyType'
				label='Currency Type'
				type='text'
				name='currencyType'
				fullWidth
				value={subscription.currencyType}
				onChange={handleChange}
			/>

			<TextField
				margin='dense'
				id='itemTypes'
				label='Item Types'
				type='text'
				name='itemTypes'
				fullWidth
				value={subscription.itemTypes}
				onChange={handleChange}
			/>

			<FormControlLabel
				control={<Checkbox name="checkedF" />}
				label="Set Active"
			/>
			<Button type='submit' color='primary' variant="contained">
				Save
			</Button>
			<Button onClick={close} color='secondary' variant="contained">
				cancel
			</Button>
		</form>

	);
}

export default SubscriptionForm;