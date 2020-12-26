import React, { Component } from "react"
import Button from "@material-ui/core/Button"
import AddAlertIcon from '@material-ui/icons/AddAlert'
import { MemoryRouter as Router } from "react-router"
import { Link as RouterLink } from "react-router-dom"


class SubscriptionButton extends Component {

    constructor() {
        super()

    }

    render() {
        return (
            <Router>
                <div className="headerIcon">
                    <Button
                        variant="outlined"
                        size="small"
                        startIcon={<AddAlertIcon />}
                        component = {RouterLink} to="/SubscriptionMenu"
                    >
                        subscription
                    </Button>
                </div>
            </Router>
        )
    }


}

export default SubscriptionButton