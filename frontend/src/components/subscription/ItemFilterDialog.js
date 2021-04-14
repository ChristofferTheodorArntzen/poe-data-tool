/* eslint-disable no-unused-vars */
import React, { Component } from "react";
import EditIcon from "@material-ui/icons/Edit";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import itemFilterTypes from "./ItemFilterTypes.json";

class ItemFilterDialog extends Component {
	constructor() {
		super();

		this.state = {
			isOpen: false,
		};

		this.handleClose = this.handleClose.bind(this);
		this.handleOpenClick = this.handleOpenClick.bind(this);
	}

	handleClose() {
		this.setState({
			isOpen: false,
		});
	}

	handleOpenClick() {
		this.setState({
			isOpen: true,
		});
	}

	render() {
		const itemFilterTypeElements = itemFilterTypes.itemTypes.map((itemType) => {
			const element = (
				<FormControlLabel
					key={itemType.id}
					value={itemType.type}
					control={<Checkbox color="primary" />}
					label={itemType.name}
					labelPlacement="end"
				/>
			);
			return element;
		});

		return (
			<div>
				<Button
					variant="contained"
					size="small"
					onClick={this.handleOpenClick}
					startIcon={<EditIcon />}
				>
					Edit filter
				</Button>

				<Dialog open={this.state.isOpen} onClose={this.handleClose}>
					<DialogTitle>ItemFilter</DialogTitle>
					<DialogContent>
						<DialogContentText>
							Check or uncheck item types you want the service to include in the
							subscription
						</DialogContentText>
                        {itemFilterTypeElements /* Add better styling to this ...*/}
					</DialogContent>
					<DialogActions>
						<Button
							variant="contained"
							size="small"
							color="primary"
							onClick={this.handleClose}
						>
							Cancel
						</Button>
						{/* maybe need another on click event */}
						<Button
							variant="contained"
							size="small"
							color="primary"
							onClick={this.handleClose}
						>
							Save
						</Button>
					</DialogActions>
				</Dialog>
			</div>
		);
	}
}

export default ItemFilterDialog;
