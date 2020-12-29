import React, { Component } from "react"
import Button from "@material-ui/core/Button"
import PersonIcon from '@material-ui/icons/Person'


class LoginButton extends Component {


    render() {
        return (
            <div className="headerIcon">
                <Button
                    variant="outlined"
                    size="small"
                    startIcon={<PersonIcon />}
                >
                    Login
            </Button>
            </div>
        )
    }


}

export default LoginButton