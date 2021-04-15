/* eslint-disable no-unused-vars */
/* eslint-disable react/prop-types */
import React, { Component } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import MenuItem from "@material-ui/core/MenuItem";

const useStyles = makeStyles((theme) => ({
	root: {
		width: 400,
	},
	pos: {
		marginBottom: 12,
	},
}));

const SubscriptionListItem = (props) => {
	
	console.log(props);

	if(props == null) {
		return;
	}

	// const {
	// 	id,
	// 	name,
	// 	tabIds,
	// 	currencyThreshold,
	// 	currencyType,
	// 	itemFilter,
	// 	isActive,
	// } = props.subscription;

	const classes = useStyles();

	return (
		<MenuItem button dense >
			<Card className={classes.cardStyle} variant="outlined">
				<CardContent>
					<Typography variant="h5" component="h5">
						place holder sub name
					</Typography>
					<Typography variant="h5" component="h5">
						{`Threshold:`}
					</Typography>
					<h3> Tabs: </h3>
					<Typography >
						tabs placeholder value
					</Typography>
					<h2> Item types </h2>
					<h2> item types place holder value </h2>
				</CardContent>
				<div>
					<CardActions>
						<Button variant="contained" color="primary">
							Edit
								</Button>
						<Button variant="contained" color="secondary">
							Delete
								</Button>
					</CardActions>
				</div>
			</Card>
		</MenuItem>
	)
}

export default SubscriptionListItem;
