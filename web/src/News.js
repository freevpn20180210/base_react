import React, {Component} from 'react';
import Axios from "axios";
import {DatePicker, message, Pagination} from 'antd';
import Form from "./Form";

//表头
class TableHeader extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <thead>
            <tr>
                <th>标题</th>
                <th>图片</th>
                <th>更新时间</th>
                <th>操作</th>
            </tr>
            </thead>
        )
    }
}

//表体
class TableBody extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        const rows = this.props.data.map((item, index) => {
            return (
                <tr key={index}>
                    <td><a href={item.path} target='_blank'>{item.title}</a></td>
                    <td><img src={item.image}/></td>
                    <td>{item.passtime}</td>
                    <td>
                        <button className="btn btn-primary" onClick={() => {

                        }}>修改
                        </button>
                        <button className="btn btn-danger" style={{marginLeft: '5px'}} onClick={() => {
                            this.props.deleteOne(item.id)
                        }}>删除
                        </button>
                    </td>
                </tr>
            )
        })
        return (
            <tbody>{rows}</tbody>
        )
    }
}

class News extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: '',
            title: '',
            path: '',
            image: '',
            passtime: '',
            data: [],
            count: '',
        }
    }

    componentDidMount() {
        this.query();
    }

    //查询
    query = page => {
        Axios.post('/news_list', {page: page}).then(({data}) => {
            this.setState({
                data: data.data,
                count: data.count
            })
        })
    }

    //删除
    deleteOne = id => {
        Axios.delete(`/news_del?ids=${id}`).then(({data}) => {
            if (data.ok) {
                this.query()
            } else {
                alert('删除失败')
            }
        })
    }

    //分页
    onChange = pageNumber => {
        this.query(pageNumber)
    }

    render() {
        return (
            <div className="container">
                <table>
                    <TableHeader/>
                    <TableBody data={this.state.data} deleteOne={this.deleteOne}/>
                </table>
                <Pagination showQuickJumper defaultCurrent={1} total={this.state.count} onChange={this.onChange}/>
                <Form query={this.query}/>
            </div>
        )
    }
}

export default News
