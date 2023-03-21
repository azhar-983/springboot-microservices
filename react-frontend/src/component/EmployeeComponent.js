import React, { Component } from 'react';
import EmployeeService from '../service/EmployeeService';

class EmployeeComponent extends Component {
    constructor(props) {
        super(props);
        this.state={
            employee:{},
            department:{}
        }
    }
    componentDidMount(){
        EmployeeService.getEmployee().then((response)=>{
            this.setState({employee:response.data.employee})
            this.setState({department:response.data.department})

            console.log(this.state.employee)
            console.log(this.state.department)
        })
    }
    render() {
        return (
            <div>
                {/* <div className='container card offset-md-3'>
                    <h3 className='text-center card-header'>View Employee Details</h3>
                    <div className='card-bod'>
                        <div className='row'>
                            <p><strong>Employee First Name:</strong>{this.state.employee.firstName}</p>
                        </div>
                        <div className='row'>
                            <p><strong>Employee Last Name:</strong>{this.state.employee.lastName}</p>
                        </div>
                        <div className='row'>
                            <p><strong>Employee Email:</strong>{this.state.employee.email}</p>
                        </div>
                    </div>
        </div> */}
            </div>
        );
    }
}

export default EmployeeComponent;