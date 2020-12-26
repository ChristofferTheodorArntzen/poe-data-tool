import React from 'react';

class ButtonsView extends React.Component {

    constructor(props)
    {
        super(props);
        this.state = {isToggleOn: true}
        this.handleClick = this.handleClick.bind(this);
      
    }

    handleClick() {
      this.setState(state => ({
        isToggleOn: !state.isToggleOn
      }));
    }

    render() {
      //  const button = <button onClick={ItemView}> public </button>
      //  const button2 = <button onClick={tabView}> private </button>
        return (
          <button onClick={this.handleClick}>
            {this.state.isToggleOn  ? 'ON' : 'OFF'}
          </button>
        )
    }
  }

export default ButtonsView;