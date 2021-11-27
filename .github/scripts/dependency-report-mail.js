module.exports = ({ }) => {
  const execSync = require('child_process').execSync
  execSync(`npm install nodemailer`) // Install nodemailer
  
  const nodemailer = require('nodemailer')
  const transporter = nodemailer.createTransport({
    host: "smtp.gmail.com", // Host for gmail
        port: 587,
        secureConnection: false,
        auth: {
            user: `${process.env.MAIL_USERNAME}`,
            pass: `${process.env.MAIL_PASSWORD}`
        },
        tls: {
            ciphers: 'SSLv3'
        }
  });
  
  const mailOptions = {
    from: {
      name: 'Dependency Workflow',
      address: process.env.MAIL_USERNAME
    },
    to: 'rquadros95@gmail.com',
    subject: 'Dependency update report of FootieScore',
    text: 'Please find attached report.',
    attachments: [
      {
        path: 'build/dependencyUpdates/dependency_update_report.txt'
      }
    ]
  };
  
  transporter.sendMail(mailOptions, (error, _) => {
    if (error) {
      console.log(error)
    }
  });
}
