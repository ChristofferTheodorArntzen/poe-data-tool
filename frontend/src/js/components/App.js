import './App.css';
import LoginView from './ItemView';
import React from "react";

export default class Home extends Component {
  constructor(props){
    super(props);

    this.handleSuccessfulAuth = this.handleSuccessfulAuth.bind(this);
  }



    handleSuccessfulAuth(data) {
      this.props.handleLogin(data);
      this.props.history.push("/ItemView");
    }

    render() {
      return (
        <div>
            <h1>Home: </h1>
            <h1>Test: </h1>
            <Registration handleSuccessfulAuth = {this.handleSuccessfulAuth} />
        </div>
      )
    }

}



// const App = () => (
//   <>
//     <div>
//      <ItemView/>
//     </div>
//   </>
// );

// export default App;


//function App() {

//  return (
//    <div className="App" style={{ backgroundColor: '#222' }}>
//       <ButtonsView/>
//     </div>
      
//   );
// }
// export default App;

// https://www.valentinog.com/blog/redux/

