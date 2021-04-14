import React, { Component } from "react";
import Button from "@material-ui/core/Button";
import PersonIcon from "@material-ui/icons/Person";
import { Link } from "react-router-dom";

class LoginButton extends Component {
	render() {
		return (
			<div className="headerIcon">
				<Button
					variant="outlined"
					size="small"
					startIcon={<PersonIcon />}
					component={Link}
					to={"/LoginView"}
				>
					Login
				</Button>
			</div>
		);
	}
}

export default LoginButton;
