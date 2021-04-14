/* eslint-disable no-unused-vars */
/* eslint-disable react/prop-types */
import React, { Component } from "react";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import OutlinedInput from "@material-ui/core/OutlinedInput";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import { withStyles } from "@material-ui/core/styles";
import PropTypes from "prop-types";
import CurrencyDataDump from "./CurrencyDataDump.json";
import ItemFilterDialog from "./ItemFilterDialog";
import ItemFilterList from "./ItemFilterList";
import Chip from "@material-ui/core/Chip";
import Input from "@material-ui/core/Input";
import Button from "@material-ui/core/Button";

const styles = (theme) => ({
	formControl: {
		marginTop: theme.spacing(2),
		minWidth: 250,
		width: 500,
	},
	selectEmpty: {
		marginTop: theme.spacing(2),
	},
	chips: {
		display: "flex",
		flexWrap: "wrap",
	},
	chip: {
		margin: 2,
	},
});

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
	PaperProps: {
		style: {
			maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
			width: 250,
		},
	},
};

class SubscriptionForm extends Component {
	constructor() {
		super();

		this.state = {
			name: "",
			currencyType: "",
			currencyThreshold: "",
			itemFilter: [],
			currencyTypeList: [],
			tabIds: ["tab 1", "tab 2"],
		};

		this.handleInputChange = this.handleInputChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	componentDidMount() {}

	handleInputChange(event) {
		const value = event.target.value;
		const fieldName = event.target.name;

		this.setState({
			[fieldName]: value,
		});
		console.log("event value: " + value);
	}

	handleSubmit(event) {
		const url = "http://localhost:8080/subscription";

		const { name, currencyType, currencyThreshold } = event.target;

		const subscription = {
			name: name.value,
			tabIds: ["tab1", "tab2"],
			currencyThreshold: currencyThreshold.value,
			itemFilter: "placeholder",
			currencyType: currencyType.value,
		};

		fetch(url, {
			method: "POST",
			body: JSON.stringify(subscription),
			headers: {
				"Content-Type": "application/json",
			},
			credentials: "same-origin",
		}).then((response) => {
			console.log("did it " + response);
		});

		event.preventDefault();
	}

	render() {
		const { classes } = this.props;

		return (
			<form onSubmit={this.handleSubmit}>
				<FormControl variant="outlined" className={classes.formControl}>
					<InputLabel htmlFor="component-outlined">Name</InputLabel>
					<OutlinedInput
						id="name"
						name="name"
						value={this.state.name}
						autoComplete="off"
						onChange={this.handleInputChange}
						label="Name"
					/>
				</FormControl>
				<br />
				<FormControl variant="outlined" className={classes.formControl}>
					<InputLabel id="currency-type">Currency Type</InputLabel>
					<Select
						name="currencyType"
						label="Currency Type"
						labelId="currecy-type"
						id="select-currency-type"
						value={this.state.currencyType}
						onChange={this.handleInputChange}
					>
						{CurrencyDataDump.currencyDetails.map((currencyType) => (
							<MenuItem key={currencyType.id} value={currencyType.tradeId}>
								{currencyType.name}
							</MenuItem>
						))}
					</Select>
				</FormControl>
				<br />
				<FormControl variant="outlined" className={classes.formControl}>
					<InputLabel htmlFor="component-outlined">
						Currency Treshold
					</InputLabel>
					<OutlinedInput
						autoComplete="off"
						id="currencyThreshold"
						name="currencyThreshold"
						type="number"
						value={this.state.treshold}
						onChange={this.handleInputChange}
						label="Currency Treshold"
					/>
				</FormControl>
				<br />
				<FormControl variant="outlined" className={classes.formControl}>
					<InputLabel id="multiple-chip-label"> Selected Tabs </InputLabel>
					<Select
						name="tabIds"
						labelId="multiple-chip-label"
						id="multiple-chip"
						value={this.state.tabIds}
						onChange={this.handleInputChange}
						input={<Input id="select-multiple-chip" />}
						renderValue={(selected) => (
							<div className={classes.chips}>
								{selected.map((value) => {
									console.log(value);
									<Chip key={value} label={value} className={classes.chip} />;
								})}
							</div>
						)}
						MenuProps={MenuProps}
					></Select>
				</FormControl>
				<div className="item-filter-container">
					<div className="active-item-filter-list-container">
						<h2 className="active-item-filter-list-header"> Active items </h2>
						<br />
						<ItemFilterList />
					</div>
					<div className="edit-item-filter-button">
						<ItemFilterDialog />
					</div>
				</div>
				<div className="form-save-button">
					<Button variant="contained" type="submit">
						Save
					</Button>
				</div>
			</form>
		);
	}
}

SubscriptionForm.propTypes = {
	classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(SubscriptionForm);
