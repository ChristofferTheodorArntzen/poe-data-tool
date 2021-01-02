/* eslint-disable no-unused-vars */
/* eslint-disable react/prop-types */
import React, { Component } from "react";
import { withStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";
import MenuItem from "@material-ui/core/MenuItem";

const styles = () => ({
	root: {
		width: 400,
	},
	selected: {
		backgroundColor: "#ffffff",
        color: "black",
        width: 400
	},
	pos: {
		marginBottom: 12,
	},
});

class SubscriptionListItem extends Component {
	constructor(props) {
		super(props);

		this.state = {
			selected: null,
		};
	}

	updateSelected(selectedIndex) {
		if (this.state.selected == selectedIndex) {
			this.setState({
				selected: null,
			});
		} else {
			this.setState({
				selected: selectedIndex,
			});
		}
	}

	render() {
		const { classes } = this.props;
		const { selected } = this.state;

		const cards = [];

		for (const [index, subscription] of this.props.subscriptions.entries()) {
			const {
				id,
				name,
				tabIds,
				currencyThreshold,
				currencyType,
				itemFilter,
				isActive,
			} = subscription;

			const itemFilterElements = itemFilter.map((itemFilter, index) => (
				<Typography
					key={"item-filter" + itemFilter.id}
					variant="p"
				>{`${itemFilter.name}, `}</Typography>
			));

            
            let cardStyle = null;
            if(selected == index){
                cardStyle = classes.selected
            } else {
                cardStyle = classes.root
            }

			cards.push(
				<MenuItem
                    button
                    dense
					onClick={() => this.updateSelected(index)}
					selected={selected == index}
					key={id}
				>
					<Card className={cardStyle} variant="outlined">
						<CardContent>
							<Typography variant="h5" component="h5">
								{name}
							</Typography>
							<Typography variant="h5" component="h5">
								{`Threshold: ${currencyThreshold} - ${currencyType}`}
							</Typography>
							<h3> Tabs: </h3>
							<Typography key={"tab-" + index}>
								{tabIds.map((tab) => `${tab}, `)}
							</Typography>
							<h2> Item Filter </h2>
							<Typography key={"item-filter" + index}>
								{itemFilter.map((itemFilterItem) => `${itemFilterItem.name}, `)}
							</Typography>
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
			);
		}

		return <div className="card-list-item">{cards}</div>;
	}
}

export default withStyles(styles)(SubscriptionListItem);
