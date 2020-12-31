/* eslint-disable react/prop-types */
import React, { Component } from "react";
import {
	TextField,
	Paper,
	MenuItem,
	Button,
	withStyles,
} from "@material-ui/core";
import "./LoginView.css";

const useStyles = (theme) => ({
	root: {
		display: "flex",
		flexWrap: "wrap",
		margin: "0",
		"& > *": {
			margin: theme.spacing(1),
			width: theme.spacing(16),
			height: theme.spacing(16),
		},
	},
});

class LoginView extends Component {
	constructor(props) {
		super(props);

		this.state = {
			league: "",
			accountName: "",
			realm: "",
			sessionId: "",
		};

		this.handleChange = this.handleChange.bind(this);
	}

	handleChange(event) {
		const stateName =
			event.target.id === undefined ? "league" : event.target.id;
		const value = event.target.value;

		this.setState({
			[stateName]: value,
		});
	}
	// find out why eslint disagrees with arrow functions in classes ...
	handleSubmit(event) {
		event.preventDefault();

		console.log("This is the state at submit");
		console.log(this.state);
		fetch("http://localhost:8080/user", {
			method: "POST",
			headers: {
				Accept: "application/json",
				"Content-Type": "application/json",
			},
			body: JSON.stringify(this.state),
		}).then((response) => {
			//TODO: maybe show a logged in state / remove form
			if (response.status >= 200 && response.status < 300)
				window.location.reload();
		});
	}

	validate() {}

	render() {
		const leagues = ["temp standard", "temp Hardcore", "Standard", "Hardcore"];

		const classes = this.props;

		return (
			<main>
				<div className="form-container">
					<form onSubmit={this.handleSubmit}>
						<div className={classes.root}>
							<Paper elevation={3}>
								<TextField
									required
									fullWidth
									id="accountName"
									label="Account Name"
									variant="filled"
									helperText="The username you log in with at"
									value={this.state.accountName}
									onChange={this.handleChange}
								/>
								<TextField
									required
									fullWidth
									id="league"
									select
									label="Select League"
									variant="filled"
									value={this.state.league}
									onChange={this.handleChange}
									helperText="Please select a league"
								>
									{leagues.map((string) => (
										<MenuItem key={string} value={string}>
											{string}
										</MenuItem>
									))}
								</TextField>
								<TextField
									required
									fullWidth
									id="realm"
									label="Realm"
									variant="filled"
									value={this.state.realm}
									onChange={this.handleChange}
								/>
								<TextField
									required
									fullWidth
									id="sessionId"
									label="SessionId"
									variant="filled"
									value={this.state.sessionId}
									onChange={this.handleChange}
								/>
								<Button variant="contained" color="primary" type="submit">
									Submit
								</Button>
							</Paper>
						</div>
					</form>
				</div>
			</main>
		);
	}
}

export default withStyles(useStyles)(LoginView);
