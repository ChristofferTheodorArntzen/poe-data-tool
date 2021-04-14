import React, { Component } from "react"
import Button from "@material-ui/core/Button"
import SettingsIcon from "@material-ui/icons/Settings"

class SettingsButton extends Component {

    constructor(props) {
        super(props)

        this.state = {
        }

        this.handleOnClick = this.handleOnClick.bind(this)
    }

    handleOnClick() {

    }

    render() {
        return (
            <div className="headerIcon">
                <Button
                    variant="outlined"
                    size="small"
                    startIcon={<SettingsIcon />}
                    onClick={this.handleOnClick}
                >
                    settings
            </Button>
            </div>
        )
    }
}

export default SettingsButton