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
			CurrencyTreshold: "",
			itemFilter: [],
			currencyTypeList: [],
			tabIds: ["tab 1", "tab 2"],
		};

		this.handleInputChange = this.handleInputChange.bind(this);
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

	render() {
		const { classes } = this.props;

		return (
			<form>
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
						{/* TODO: optimize this. really slow to open - maybe add default to 1ex */}
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
						id="CurrencyTreshold"
						name="CurrencyTreshold"
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
            renderValue={ /*figure out how this works*/ (selected) => (
							<div className={classes.chips}>
								{selected.map((value) => (
									<Chip key={value} label={value} className={classes.chip} />
								))}
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
			</form>
		);
	}
}

SubscriptionForm.propTypes = {
	classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(SubscriptionForm);
