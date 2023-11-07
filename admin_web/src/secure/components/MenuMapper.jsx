import "../css/menuMapper.css"

export default function MenuMapper({ data }) {

    const temp = [
        {
            name: "pizza margaritha",
            price: 1999,
            rating: 4
        },
        {
            name: "pizza california",
            price: 3140,
            rating: 3
        },
        {
            name: "pizza szexard",
            price: 2490,
            rating: 5
        },
    ]

    const convertRatings = (number) => "*".repeat(number);
    
    

    const tableHeader = Object.keys(temp[0]).map((key, i) => {
        return (<th key={i}>{key.toUpperCase()}</th>)
    })

    const content = temp.map((food, i) => (<tr key={i}>
        <td>{food.name}</td>
        <td>{food.price}</td>
        <td>{convertRatings(food.rating)}</td>
        <td>edit</td>
        <td>remove</td>
    </tr>))

    return (
    <>
        <table className="table">
            <thead>
                <tr>
                    {/* {tableHeader} */}
                    <th>Name</th>
                    <th>Price</th>
                    <th>Rating</th>
                    <th>Edit</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                    {content}
            </tbody>
        </table>
    </>
    )
}